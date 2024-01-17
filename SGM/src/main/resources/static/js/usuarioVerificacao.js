async function verificarUsuario() {
	const erroUsername = document.getElementById('erroUsername');
    const username = document.getElementById('username').value;
    erroUsername.textContent = '';
   
    try {
        const response = await fetch(`/requisicoesrest/verificarusuarioexistente/${username}`, {
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
         	
            erroUsername.textContent = 'Username indisponível ';
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
    
    const usuarioValido = await verificarUsuario();

    
    if (!usuarioValido) {
    	 
        return false; // Impede o envio do formulário
    }
    
    // Continue com o envio do formulário se o usuário for válido
 
    this.submit();
});

