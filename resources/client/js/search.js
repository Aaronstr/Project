function pageLoad() {


    fetch("/Quiz/Select", {method: 'get'}//calls API login in the quiz controller
    ).then(response => response.json()
    ).then(quizzes => {
        if (quizzes.hasOwnProperty('error')) {
            alert(quizzes.error);
        } else {
            let resultsDiv = document.getElementById("searchResults");
            let resultsHTML = "";

            for (let quiz of quizzes) {
                resultsHTML += `<div> <a href="/client/quiz.html?id=${quiz.QuizID}" style="color: black;text-decoration: none;"> QUIZID: ${quiz.QuizID} --- QUIZNAME: ${quiz.QuizName} --- USERNAME: ${quiz.Username}</a></div>`;
            }
            resultsDiv.innerHTML = resultsHTML;


        }
    });
//" style="text-decoration: none;"
}






