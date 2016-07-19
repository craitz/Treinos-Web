$(function() {
	$('[rel = "tooltip"]').tooltip();
});

$('#confirmacaoModal').on('show.bs.modal', function(event) {
	var button = $(event.relatedTarget);
	var id = button.data('id');
	var nome = button.data('nome');
	var modal = $(this);
	var form = modal.find('form');
	var action = form.data('url-base');
	
	action = (!action.endsWith('/')) ? (action + '/') : action;
	
	form.attr('action', action + id);
	modal.find('.modal-body span').html('Tem certeza que deseja excluir o título <strong>' + nome + '</strong> ?');
});