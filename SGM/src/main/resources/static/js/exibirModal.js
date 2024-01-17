function exibirModal() {
    const modal = document.getElementById("modal");
    const mensagemModal = document.getElementById("mensagemModal");
	
	
    // Verifique se o elemento h3 possui algum texto
   if (mensagemModal.innerText.trim() !== "") {
	   	
        modal.style.bottom = "0"; // Move o modal para cima
        setTimeout(() => {
            fecharModal();
        }, 2000); // Fecha o modal após 2 segundos (ajuste conforme necessário)
   }
}
function fecharModal() {
    const modal = document.getElementById("modal");
    modal.style.bottom = "-100%"; // Move o modal de volta para baixo
}

document.addEventListener("DOMContentLoaded", function() {
   
    exibirModal();
    

});