
async function verificarMedicamento() {
	const erroMedicamento = document.getElementById('erroMedicamento');
	
    const nomeMedicamento = document.getElementById('nome').value;
    console.log(nomeMedicamento);
    erroMedicamento.textContent = '';
    
    try {
        const response = await fetch(`/requisicoesrest/verificarmedicamentoexistente/${nomeMedicamento}`, {
            method: 'GET',
            headers: {
                'Accept': 'text/plain'
            }
        });

        if (!response.ok) {
            throw new Error('Erro ao verificar o medicamento');
        }

        const isTrue = (await response.text()).trim() === 'true';

        if (isTrue) {
          
            erroMedicamento.textContent = 'Medicamento já consta no sistema ';
           
            return false;
        } else {
			 
            return true;
        }
    } catch (error) {
        console.error('Ocorreu um erro:', error);
        return false; // Trate o erro e retorne false
    }


}

document.querySelector('form').addEventListener('submit', async function (e) {
    e.preventDefault(); // Impede o envio padrão do formulário
    
    const medicamentoValido = await verificarMedicamento();

    
    if (!medicamentoValido) {
     	
        return false; // Impede o envio do formulário
    }
    
    // Continue com o envio do formulário se o usuário for válido
    
    this.submit();
});


