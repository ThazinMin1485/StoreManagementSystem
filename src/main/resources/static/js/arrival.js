let goodId;
let selectedValue;
let quantity;
let quantityValue=0;
let g;
let gValue=0;
let percentValue;
let fromDateValue;
let toDateValue;
let sellingPrice;

document.addEventListener("DOMContentLoaded", () => {
goodId = document.getElementById("good_id");
 if(goodId) {
 selectedValue = goodId.value;
   getChangeValue(goodId, "goodId");
            }
            quantity = document.getElementById("quantity");
            quantityValue = quantity.value;
            console.log(quantityValue)
                setInputValue(quantity, "quantity");
                g = document.getElementById("g");
                gValue = g.value;
                setInputValue(g, "g");
            let percent = document.getElementById("percent");
            percentValue = percent.value;
            setInputValue(percent,"percent");
            let fromDate = document.getElementById("fromDate");
            fromDateValue = fromDate.value;
            getChangeValue(fromDate, "fromDate");
            let toDate = document.getElementById("toDate");
            toDateValue =toDate.value;
            getChangeValue(toDate, "toDate");
           sellingPrice = document.getElementById("sellingPrice");
           toggleInputs();
           goodId.addEventListener("change", toggleInputs);
})

function setInputValue(selectId, value) {
 selectId.addEventListener("keyup", function () {
 switch(value) {
 case "quantity" : quantityValue = this.value;
 break;
 case "g" : gValue = this.value;
 break;
 case "percent" : percentValue = this.value;
 break;
 }
fetchSellingPriceData();
});
}

function getChangeValue(selectId, value) {
selectId.addEventListener("change", function () {
switch(value) {
 case "goodId" : selectedValue = selectId.value;
 break;
 case "fromDate" : fromDateValue = selectId.value;
 break;
 case "toDate" : toDateValue = selectId.value;
 break;
 }
 fetchSellingPriceData();
 });
}

function fetchSellingPriceData() {
console.log(selectedValue, quantityValue,gValue,percentValue,fromDateValue,toDateValue)
if(isValid(selectedValue) && (isValid(quantityValue) || isValid(gValue)) && isValid(percentValue) && isValid(fromDateValue) && isValid(toDateValue)){
fetch(`/goodDetail/arrival?goodId=${selectedValue}&quantity=${quantityValue}&g=${gValue}&percent=${percentValue}&fromDate=${fromDateValue}&toDate=${toDateValue}`)
            .then(res => res.json())
            .then(data => {
            sellingPrice.value = data;
            });
}
}

function toggleInputs() {
const selectedOption = goodId.options[goodId.selectedIndex];
const isKilo = selectedOption.getAttribute("data-kilo") == "true";
if(isKilo) {
quantity.disabled = true;
g.disabled = false;
} else {
quantity.disabled = false;
g.disabled = true;
}
}

function isValid(value) {
if(value != undefined && value !="" && value!= 0) {
return true;
} else {
return false;
}
}

