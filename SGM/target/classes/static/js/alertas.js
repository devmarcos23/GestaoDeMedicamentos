const statusEstoque = document.getElementById('status-estoque')
const statusVencimento = document.getElementById('status-vencimento')
const quantidadeVencimento = document.getElementById('quantidade-vencimento')
const h3StatusEstoque = document.getElementById('h3-status-estoque')
const imgStatusEstoque = document.getElementById('img-status-estoque')

if (quantidadeVencimento.textContent == '' || quantidadeVencimento.textContent == 0) {
    statusEstoque.classList.remove('status-ruim')
    statusEstoque.classList.add('status-bom')
    h3StatusEstoque.innerHTML = 'Bom'
    //statusVencimento.classList.add('none')
    imgStatusEstoque.src = '/img/health.png'
}