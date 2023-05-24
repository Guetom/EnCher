
const radio1 = document.getElementById("radio1");
const radio2 = document.getElementById("radio2");
const checkboxesRadio1 = document.querySelectorAll("#radio1 ~ ul input[type='checkbox']");
const checkboxesRadio2 = document.querySelectorAll("#radio2 ~ ul input[type='checkbox']");

const addListenerForCheckedRadioButton = (radioElement, otherRadioElement, checkboxesElement, otherCheckboxesElement) => {
    radioElement.addEventListener("change", function () {
    	radioElement.checked = true;
    	otherRadioElement.checked = false;
        changeStateAllCheckboxes(checkboxesElement, radioElement.disabled);
        changeStateAllCheckboxes(otherCheckboxesElement, !radioElement.disabled);
    });
}

const changeStateAllCheckboxes = (checkboxesElement, isDisabled) => {
    checkboxesElement.forEach(function (checkbox) {
        checkbox.disabled = isDisabled;
        checkbox.checked = !isDisabled;
    });
}

addListenerForCheckedRadioButton(radio1, radio2, checkboxesRadio1, checkboxesRadio2);
addListenerForCheckedRadioButton(radio2, radio1, checkboxesRadio2, checkboxesRadio1);
