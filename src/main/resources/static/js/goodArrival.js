let goodSelect;
let quantityInput;
let kgInput;

document.addEventListener("DOMContentLoaded", () => {
goodSelect = document.getElementById("selectGood");
quantityInput = document.getElementById("quantityInput");
kgInput = document.getElementById("kgInput");
toggleInputs();
goodSelect.addEventListener("change", toggleInputs);
})

function toggleInputs() {
const selectedOption = goodSelect.options[goodSelect.selectedIndex];
const isKilo = selectedOption.getAttribute("data-kilo") == "true";
if(isKilo) {
quantityInput.disabled = true;
kgInput.disabled = false;
} else {
quantityInput.disabled = false;
kgInput.disabled = true;
}
}