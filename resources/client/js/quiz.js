// document.getElementById("questionHeader").innerHTML = Question.QuestionText; // loads the question into the html
var score=0;


function pageLoad() {

    let queryString = window.location.search;

    let QuizID = queryString.slice(1);

    //QUIZNAME//
    fetch("/Quiz/Select3/" + QuizID, {method: 'get'}//calls API get QuizName, I load it to make sure it is up to date.
    ).then(response => response.json()
    ).then(responseData => {

        if (responseData.hasOwnProperty('error')) {
            alert(responseData.error);
        } else {
            let QuizName = responseData.QuizName;
            document.getElementById("quizHeader").innerHTML = QuizName; //Creates a header with thw quizzes name

        }
    });


    //QUIZ//

    fetch("/Question/Select/" + QuizID, {method: 'get'} //Load the question
    ).then(response => response.json()
    ).then(Questions => {
        for (let Question of Questions) {
            let QuestionID = Question.QuestionID;

            let QuestionType = Question.QuestionType;

            if (QuestionType == 2) { //Finds out the question type
                fetch("/AnswerTypeTwo/Select/" + QuestionID, {method: 'get'} //loads correct and wrong answer
                ).then(response2 => response2.json()
                ).then(Answer => {
                    //creates table
                    let QuestionsHTML = `<table id="table_${Question.QuestionID}">` +
                        '<tr>' +
                        '<th>QuestionID</th>' +
                        '<th>QuestionType</th>' +
                        '<th>QuestionText</th>' +
                        '<th>Answer1</th>' +
                        '<th>Answer2</th>' +
                        '<th>Answer3</th>' +
                        '<th>Answer4</th>' +
                        '</tr>';
                    //Places data into table
                    QuestionsHTML += '<tr>' +
                        `<td>${Question.QuestionID}</td>` +
                        `<td>${Question.QuestionType}</td>` +
                        `<td>${Question.QuestionText}</td>` +
                        `<td>${Answer.CorrectAnswerText}</td>` +
                        `<td>${Answer.WrongAnswerText1}</td>` +
                        `<td>${Answer.WrongAnswerText2}</td>` +
                        `<td>${Answer.WrongAnswerText3}</td>` +
                        `<td class ='last'>` +
                        '<div>' +
                        `    <input type="text" name ="Answer" id="Answer${Question.QuestionID}">` +
                        '</div>' +
                        `<button type="submit" class ='test' data-id='${Question.QuestionID}' data-id2='${Question.QuestionType}'>Mark</button>` +
                        `</td>` +
                        `</tr>`;
                    //Above it creates a form to enter the answer in and a button to submit it.

                    QuestionsHTML += '</table>';

                    document.getElementById("Quiz").innerHTML += QuestionsHTML;

                    let editButtons = document.getElementsByClassName("test"); //  This button takes you to the function checkQuestion
                    for (let button of editButtons) {
                        button.addEventListener("click", checkQuestion,);
                    }


                });
                //It then checks if it is a question type one

            }
            //It then checks if it is a question type one

            if (QuestionType == 1) { //Finds out the question type
                fetch("/AnswerTypeOne/Select/" + QuestionID, {method: 'get'} //loads correct and wrong answer
                ).then(response2 => response2.json()
                ).then(Answer => {
                    //creates table
                    let QuestionsHTML = `<table id="table_${Question.QuestionID}" >` +
                        '<tr>' +
                        '<th>QuestionID</th>' +
                        '<th>QuestionType</th>' +
                        '<th>QuestionText</th>' +
                        '</tr>';
                    //Places data into table
                    QuestionsHTML += '<tr>' +
                        `<td>${Question.QuestionID}</td>` +
                        `<td>${Question.QuestionType}</td>` +
                        `<td>${Question.QuestionText}</td>` +
                        `<td class ='last'>` +
                        '<div>' +
                        `    <input type="text" name ="Answer" id="Answer${Question.QuestionID}">` +
                        '</div>' +
                        `<button type="submit" class ='test' data-id='${Question.QuestionID}' data-id2='${Question.QuestionType}'>Mark</button>` +
                        `</td>` +
                        `</tr>`;
                    //Above it creates a form to enter the answer in and a button to submit it.

                    QuestionsHTML += '</table>';

                    document.getElementById("Quiz").innerHTML += QuestionsHTML;

                    let editButtons = document.getElementsByClassName("test"); //  This button takes you to the function checkQuestion
                    for (let button of editButtons) {
                        button.addEventListener("click", checkQuestion,);
                    }


                });
            }
        }


    });

    document.getElementById("submit").addEventListener("click", finish); // takes you to submit

}


function checkQuestion(event){ //This will check if the question is correct

    let id = event.target.getAttribute("data-id"); //Gets the questionID
    let answer = document.getElementById("Answer" + id).value; //Get the data in the form
    let QuestionType = event.target.getAttribute("data-id2"); //Get the QuestionType

    if (QuestionType==2){ //Checks the QuestionType
        fetch("/AnswerTypeTwo/Select/" + id, {method: 'get'} //Loads answer
        ).then(response2 => response2.json()
        ).then(Answer => {
            CorrectAnswerText=Answer.CorrectAnswerText
            if(answer==CorrectAnswerText){//compares answers
                alert("correct");
                score=score+1;
                document.getElementById("table_" + id).style.display = "none";
            }
            else{
                alert("Incorrect, the correct answer is "+CorrectAnswerText)
                document.getElementById("table_" + id).style.display = "none";
            }
        });
    }
    if (QuestionType==1){ //Checks the QuestionType
        fetch("/AnswerTypeOne/Select/" + id, {method: 'get'} //Loads answer
        ).then(response2 => response2.json()
        ).then(Answer => {
            CorrectAnswerText=Answer.CorrectAnswerText
            if(answer==CorrectAnswerText){//compares answers
                alert("correct");
                 score=score+1
                document.getElementById("table_" + id).style.display = "none";
            }
            else{
                alert("Incorrect, the correct answer is "+CorrectAnswerText)
                document.getElementById("table_" + id).style.display = "none";

            }
        });
    }

}
function finish() {
alert("Score : " + score)
    window.location.href = '/client/search.html';
}