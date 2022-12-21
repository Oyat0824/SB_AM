$('select[data-value]').each(function(idx, element) {
	const el = $(element);
	const defaultValue = el.attr('data-value').trim();

	if (defaultValue.length > 0) {
		el.val(defaultValue);
	}
})