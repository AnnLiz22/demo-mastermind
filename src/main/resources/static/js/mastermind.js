let selectedColors = [];

document.addEventListener("DOMContentLoaded", () => {
    document.querySelectorAll(".color-button").forEach(button => {
        button.addEventListener("click", () => {
            const color = button.classList[1];
            selectColor(color, button);
        });
    });

    const resetButton = document.querySelector("#resetButton");
    if (resetButton) {
        resetButton.addEventListener("click", resetSelection);
    }
});

function selectColor(color, button) {
    if (selectedColors.length < 4 && !selectedColors.includes(color)) {
        selectedColors.push(color);
        button.classList.add("selected");
    }
    if (selectedColors.length === 4) {
        document.getElementById("guessInput").value = selectedColors.join(",");
        console.log("Selected colors:", selectedColors);
    }
}

function resetSelection() {
    selectedColors = [];
    document.getElementById("guessInput").value = "";
    document.querySelectorAll(".color-button").forEach(button => {
        button.classList.remove("selected");
    });
    console.log("Selection reset");
}
