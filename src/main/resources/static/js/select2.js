// Дождемся полной загрузки jQuery и Select2
(function() {
    'use strict';

    // Проверяем доступность jQuery и Select2
    if (typeof jQuery === 'undefined' || typeof jQuery.fn.select2 === 'undefined') {
        console.error('jQuery или Select2 не загружен');
        return;
    }

    $.fn.select2.defaults.set('language', {
        noResults: () => "Brak wyników"
    });

    $(function () {

        const $brand = $('#carBrandSelect');
        const $model = $('#carModelSelect');

        if (!$brand.length || !$model.length) return;

        const preSelectedModelId = $model.data('selected-model-id') || null;

        // === INIT SELECT2 ===
        $('select.select2-dark').each(function () {
            const name = $(this).attr('name');
            let placeholder = 'Wybierz opcję';
            let minResults = 10;

            if (name === 'carBrand') placeholder = 'Wybierz markę pojazdu';
            if (name === 'carModel') {
                placeholder = 'Najpierw wybierz markę';
                minResults = Infinity;
            }
            if (name === 'carBodyType') placeholder = 'Typ nadwozia';
            if (name === 'carFuelType') placeholder = 'Rodzaj paliwa';
            if (name === 'carYear') placeholder = 'Rok produkcji';

            $(this).select2({
                placeholder: placeholder,
                allowClear: true,
                width: '100%',
                minimumResultsForSearch: minResults
            });
        });

        // === DISABLE MODEL SELECT IF NO BRAND SELECTED ===
        if (!$brand.val()) {
            $model.prop('disabled', true);
            $model.select2('destroy');
            $model.select2({
                placeholder: 'Najpierw wybierz markę',
                width: '100%',
                allowClear: true,
                minimumResultsForSearch: Infinity
            });
        }

        // === LOAD MODELS BY BRAND ===
        function loadModels(brandId, restoreValue = null) {
            // Enable model select
            $model.prop('disabled', false);

            if ($model.hasClass('select2-hidden-accessible')) {
                $model.select2('destroy');
            }

            $model.empty().append('<option value=""></option>');

            // Show loading state
            $model.select2({
                placeholder: 'Ładowanie modeli...',
                width: '100%',
                allowClear: true,
                minimumResultsForSearch: Infinity,
                disabled: true
            });

            fetch(`/models?brand=${encodeURIComponent(brandId)}`)
                .then(res => {
                    if (!res.ok) {
                        throw new Error(`HTTP error! status: ${res.status}`);
                    }
                    return res.json();
                })
                .then(models => {
                    // Re-enable and update select
                    $model.prop('disabled', false);
                    $model.select2('destroy');
                    $model.empty().append('<option value=""></option>');

                    if (!models || models.length === 0) {
                        $model.select2({
                            placeholder: 'Brak modeli dla tej marki',
                            width: '100%',
                            allowClear: true,
                            minimumResultsForSearch: Infinity
                        });
                        return;
                    }

                    models.forEach(m => {
                        $model.append(new Option(m.name, m.id));
                    });

                    $model.select2({
                        placeholder: 'Wybierz model pojazdu',
                        width: '100%',
                        allowClear: true,
                        minimumResultsForSearch: models.length > 7 ? 0 : Infinity
                    });

                    if (restoreValue) {
                        $model.val(restoreValue).trigger('change');
                    }
                })
                .catch((error) => {
                    $model.select2('destroy');
                    $model.select2({
                        placeholder: 'Błąd ładowania danych',
                        width: '100%',
                        allowClear: true,
                        minimumResultsForSearch: Infinity
                    });
                });
        }

        // === EVENTS ===
        $brand.on('select2:select select2:clear', function () {
            const brandId = $(this).val();

            if (!brandId) {
                // Disable model select when brand is cleared
                $model.prop('disabled', true);
                $model.select2('destroy');
                $model.empty().append('<option value=""></option>');
                $model.select2({
                    placeholder: 'Najpierw wybierz markę',
                    width: '100%',
                    allowClear: true,
                    minimumResultsForSearch: Infinity
                });
                return;
            }

            loadModels(brandId);
        });

        // === INIT ON PAGE LOAD (EDIT / SEARCH) ===
        if ($brand.val()) {
            loadModels($brand.val(), preSelectedModelId);
        }
    });

})();
