const screen = document.getElementById("screen");
const history = document.getElementById("history");

const buttons = document.querySelectorAll(".buttons button");

let expression = "";

buttons.forEach(button => {

    button.addEventListener("click", () => {

        const value = button.textContent;

        if (value === "C") {
            expression = "";
            screen.value = "";
            history.textContent = "";
            return;
        }
        if (value === "%") {

            let match = expression.match(/(\d+)([+\-*/])(\d+)$/)

            if (!match) return

            let base = parseFloat(match[1])
            let operator = match[2]
            let percent = parseFloat(match[3])

            let result

            if (operator === "+" || operator === "-") {
                result = base * percent / 100
            }
            else if (operator === "*" || operator === "/") {
                result = percent / 100
            }

            expression = base + operator + result
            screen.value = expression

            return
        }

        if (value === "DEL") {
            expression = expression.slice(0, -1);
            screen.value = expression;
            return;
        }

        if (value === "=") {
            try {
                history.textContent = expression;
                expression = eval(expression).toString();
                screen.value = expression;
            } catch {
                screen.value = "Error";
                expression = "";
            }
            return;
        }

        expression += value;
        screen.value = expression;

    });

});