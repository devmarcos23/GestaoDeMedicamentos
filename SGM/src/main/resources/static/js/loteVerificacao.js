function verificarDatas() {


	var dataEntrega = new Date(document.getElementById('data-entrega').value);
	var dataFabricacao = new Date(document.getElementById('data-fabricacao').value);
	var dataValidade = new Date(document.getElementById('data-vencimento').value);

	var erroEntrega = document.getElementById('erroDataEntrega');
	var erroFabricacao = document.getElementById('erroDataFabricacao');
	var erroValidade = document.getElementById('erroDataValidade');

	//caso ideal: dataFabricacao < dataEntrega < dataVencimento




	erroValidade.textContent = '';
	erroFabricacao.textContent = '';
	erroEntrega.textContent = '';

	//caso 1: fabricacao < vencimento < entrega 
	if (dataEntrega > dataValidade) {

		erroEntrega.textContent = 'Data de entrega não pode ser maior que a data de vencimento';

		return false;
	//caso 2: entrega < fabricacao < vencimento	
	} else if(dataFabricacao > dataEntrega){
		erroFabricacao.textContent = 'Data de fabricação não pode ser maior que a data de entrega';
		
		return false;
	//caso 3: entrega < vencimento < fabricacao	
	}  else if (dataValidade < dataFabricacao){
		erroValidade.textContent = 'Data de vencimento não pode ser menor que a data de fabricação';
		
		return false;
		
	} else {
		return true;
	}

}


// Adicione um ouvinte de evento para o formulário



async function verificarIdlote() {
	const erroIdLote = document.getElementById('erroIdLote');
	const idLote = document.getElementById('lote-id').value;
	erroIdLote.textContent = '';

	try {
		const response = await fetch(`/requisicoesrest/verificarloteexistente/${idLote}`, {
			method: 'GET',
			headers: {
				'Accept': 'text/plain'
			}
		});

		if (!response.ok) {
			throw new Error('Erro ao verificar o lote');
		}

		const isTrue = (await response.text()).trim() === 'true';

		if (isTrue) {

			erroIdLote.textContent = 'ID já consta no sistema';
			return false;
		} else {

			return true;
		}
	} catch (error) {
		console.error('Ocorreu um erro:', error);
		return false; // Trate o erro e retorne false
	}


}



async function verificarDatasAndInputIdLote() {

	const idloteValido = await verificarIdlote();

	if (verificarDatas() === true && idloteValido === true) {

		return true;
	} else {

		return false;
	}
}


document.querySelector('form').addEventListener('submit', async function(e) {
	e.preventDefault(); // Impede o envio padrão do formulário

	const loteValido = await verificarDatasAndInputIdLote();
	if (!loteValido) {

		return false; // Impede o envio do formulário
	}

	// Continue com o envio do formulário se o usuário for válido

	this.submit();
});



