




// Validacao campo CNPJ com mask   -- Verificar --

const cnpjInput = document.getElementById('cnpj');

cnpjInput.addEventListener('input', (e) => mascaraCnpj(e.target.value));

const mascaraCnpj = (valor) => {
    valor = valor.replace(/\D/g, ''); // Remove todos os caracteres não numéricos

    // Limita o valor a 14 dígitos
    valor = valor.substring(0, 18);

    // Aplica a máscara
    valor = valor.replace(/(\d{2})(\d{3})(\d{3})(\d{4})(\d{2})/, '$1.$2.$3/$4-$5');

    cnpjInput.value = valor; // Insere o valor formatado no campo
};




const tel = document.getElementById('tel') // Seletor do campo de telefone

tel.addEventListener('keypress', (e) => mascaraTelefone(e.target.value)) // Dispara quando digitado no campo
tel.addEventListener('change', (e) => mascaraTelefone(e.target.value)) // Dispara quando autocompletado o campo

const mascaraTelefone = (valor) => {
    valor = valor.replace(/\D/g, "")
    valor = valor.replace(/^(\d{2})(\d)/g, "($1) $2")
    valor = valor.replace(/(\d)(\d{4})$/, "$1-$2")
    tel.value = valor // Insere o(s) valor(es) no campo
}

