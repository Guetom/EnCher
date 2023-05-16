
const radio1 = document.getElementById("radio1");
const radio2 = document.getElementById("radio2");
const checkboxesRadio1 = document.querySelectorAll("#radio1 ~ ul input[type='checkbox']");
const checkboxesRadio2 = document.querySelectorAll("#radio2 ~ ul input[type='checkbox']");

const addListenerForCheckedRadioButton = (radioElement, checkboxesElement, otherCheckboxesElement) => {
    radioElement.addEventListener("change", function() {
        changeStateAllCheckboxes(checkboxesElement, radioElement.disabled);
        changeStateAllCheckboxes(otherCheckboxesElement, !radioElement.disabled);
    });
}

const changeStateAllCheckboxes = (checkboxesElement, isDisabled) => {
    checkboxesElement.forEach(function(checkbox) {
        checkbox.disabled = isDisabled;
      });
}

addListenerForCheckedRadioButton(radio1, checkboxesRadio1, checkboxesRadio2);
addListenerForCheckedRadioButton(radio2, checkboxesRadio2, checkboxesRadio1);
