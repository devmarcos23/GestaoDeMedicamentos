let data1 = new Date()

const data = document.getElementById("data")
const saudacao = document.getElementById("saudacao")
const img = document.getElementById("img")

const meses = ['Jan', 'Fev', 'Mar', 'Abr', 'Mai', 'Jun', 'Jul', 'Ago', 'Set', 'Out', 'Nov', 'Dez']

let dia = ((data1.getDate().toString()).length == 1) ? '0' + data1.getDate().toString() : data1.getDate()
let mes = meses[data1.getMonth()]

data.innerHTML = `${dia} ${mes} ${data1.getFullYear()}`

if (data1.getHours() > 5 && data1.getHours() <= 12) {
    saudacao.innerHTML = "Bom Dia"
    img.src = "/img/sun.png"
} else if (data1.getHours() > 12 && data1.getHours() < 18) {
    saudacao.innerHTML = "Boa Tarde"
    img.src = "/img/sun.png"
} else {
    saudacao.innerHTML = "Boa Noite"
    img.src = "/img/moon.png"
}