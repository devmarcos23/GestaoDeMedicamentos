

async function verificarFabricante() {
	const erroFabricante = document.getElementById('erroCnpj');
    const cnpj = document.getElementById('cnpj').value;
    const url = `/requisicoesrest/verificarfabricanteexistente?cnpj=${encodeURIComponent(cnpj)}`; // envio o cnpj como parametro para evitar problemas com caracteres especiais
   
    try {
        const response = await fetch(url, {
            method: 'GET',
            headers: {
                'Accept': 'text/plain'
            }
        });
 
        if (!response.ok) {
            throw new Error('Erro ao verificar o fabricante');
        }

        const isTrue = (await response.text()).trim() === 'true';

        if (isTrue) {
        
            erroFabricante.textContent = 'CNPJ já consta no sistema';
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
    
    const fabricanteValido = await verificarFabricante();

    
    if (!fabricanteValido) {
     	
        return false; // Impede o envio do formulário
    }
    
    // Continue com o envio do formulário se o usuário for válido
   
    this.submit();
});


