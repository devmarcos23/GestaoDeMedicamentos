function verificarDatas() {


	var dataEntrega = new Date(document.getElementById('data-entrega').value);
	var dataFabricacao = new Date(document.getElementById('data-fabricacao').value);
	var dataValidade = new Date(document.getElementById('data-vencimento').value);

	var erroEntrega = document.getElementById('erroDataEntrega');
	var erroFabricacao = document.getElementById('erroDataFabricacao');
	var erroValidade = document.getElementById('erroDataValidade');

	//caso 1


	erroEntrega.textContent = '';
	erroFabricacao.textContent = '';
	erroValidade.textContent = '';


	if (dataEntrega > dataValidade) {

		erroEntrega.textContent = "Data de entrega não pode ser maior que a data de validade";
		return false;

	} else if (dataFabricacao > dataEntrega) {

		erroFabricacao.textContent = 'Data de fabricação não pode ser maior que data de entrega';
		return false;

	} else if (dataFabricacao > dataValidade) {

		erroFabricacao.textContent = 'A data de fabricação não pode ser maior que a data de validade';
		return false;

	} else if (dataFabricacao > dataEntrega && dataEntrega > dataValidade) {

		erroEntrega.textContent = "Data de entrega não pode ser maior que a data de validade";
		erroFabricacao.textContent = 'Data de fabricação não pode ser maior que data de entrega';
		return false;

	} else if (dataFabricacao > dataValidade && dataEntrega > dataValidade) {

		erroEntrega.textContent = "Data de entrega não pode ser maior que a data de validade";
		erroFabricacao.textContent = 'A data de fabricação não pode ser maior que a data de validade';
		return false;

	} else {

		return true;
	}


}






document.querySelector('form').addEventListener('submit', function(e) {
	if (!(verificarDatas())) {
		e.preventDefault(); // Impede o envio padrão do formulário
	}
	// Impede o envio padrão do formulário

});