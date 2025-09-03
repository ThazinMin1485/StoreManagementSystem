let kiloCheck;
let quantityInput;
let kgInput;

document.addEventListener("DOMContentLoaded", () => {
kiloCheck = document.getElementById('kiloCheck');
quantityInput = document.getElementById('quantityInput');
kgInput = document.getElementById('kgInput');
toggleInputs();
kiloCheck.addEventListener("change", toggleInputs);
})

function toggleInputs() {
if(kiloCheck.checked) {
quantityInput.disabled = true;
kgInput.disabled = false;
} else {
quantityInput.disabled = false;
kgInput.disabled = true;
}
}

