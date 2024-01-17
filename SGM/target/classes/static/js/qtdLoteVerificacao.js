async function verificarQtdLote() {
	const erroUsername = document.getElementById('erroQtdLoteSaida');
    const idLote = document.getElementById('loteId').value;
    const medicamentosRetirada = document.getElementById('quantidadeRetirada').value;
    erroUsername.textContent = '';
   
   const url = `/requisicoesrest/verificarqtdloteexistente?idLote=${encodeURIComponent(idLote)}&medicamentosRetirada=${encodeURIComponent(medicamentosRetirada)}`;
    try {
        const response = await fetch( url, {
            method: 'GET',
            headers: {
                'Accept': 'text/plain'
            }
        });
 
        if (!response.ok) {
            throw new Error('Erro ao verificar o lote');
        }

        const isTrue = (await response.text()).trim() === 'disponivel';

        if (isTrue) {
         	
            return true;
        } else {
			erroUsername.textContent = 'Quantidade indisponível ';
            return false;
        }
    } catch (error) {
        console.error('Ocorreu um erro:', error);
        return false; // Trate o erro e retorne false
    }


}

document.querySelector('form').addEventListener('submit', async function (e) {
    e.preventDefault(); // Impede o envio padrão do formulário
    
    const QtdLoteValido = await verificarQtdLote();

    
    if (!QtdLoteValido) {
    	 
        return false; // Impede o envio do formulário
    }
    
    // Continue com o envio do formulário se o usuário for válido
   
    this.submit();
});
