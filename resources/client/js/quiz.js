// document.getElementById("questionHeader").innerHTML = Question.QuestionText; // loads the question into the html
var CorrectAnswerText
var WrongAnswerText1
var WrongAnswerText2
var WrongAnswerText3


function pageLoad() {

    let queryString = window.location.search;

    let QuizID = queryString.slice(1);

    //QUIZNAME//
    fetch("/Quiz/Select3/"+QuizID, {method: 'get'}//calls API get QuizName, I load it to make sure it is up to date.
    ).then(response => response.json()
    ).then(responseData => {

        if (responseData.hasOwnProperty('error')) {
            alert(responseData.error);
        } else {
            let QuizName=responseData.QuizName;
            document.getElementById("quizHeader").innerHTML = QuizName; //Creates a header with thw quizzes name

        }
    });


    //QUIZ//

    let QuestionsHTML  = '<table>' +
        '<tr>' +
        '<th>QuestionID</th>' +
        '<th>QuestionText</th>' +
        '<th>Answer1</th>' +
        '<th>Answer2</th>' +
        '<th>Answer3</th>' +
        '<th>Answer4</th>' +
        '</tr>';
    fetch("/Question/Select/" + QuizID, {method: 'get'}
    ).then(response => response.json()
    ).then(Questions => {
        for (let Question of Questions) {
            let QuestionID=Question.QuestionID;

            let QuestionType=Question.QuestionType;

            if (QuestionType=2) {
                fetch("/AnswerTypeTwo/Select/" + QuestionID, {method: 'get'}
                ).then(response2 => response2.json()
                ).then(Answer => {

               //     for (let i = Answer.length - 1; i > 0; i--) { // Shuffle the answers!
                 //       let j = Math.floor(Math.random() * (i + 1));
                //        let temp = Answer[i];
                //        Answer[i] = Answer[j];
                //        Answer[j] = temp;
                //   }

                     CorrectAnswerText=Answer.CorrectAnswerText;
                     WrongAnswerText1=Answer.WrongAnswerText1;
                     WrongAnswerText2=Answer.WrongAnswerText2;
                     WrongAnswerText3=Answer.WrongAnswerText3;

                });
            }
            //Answer.CorrectAnswerText
            //Answer.WrongAnswerText1


            QuestionsHTML += '<tr>' +
                `<td>${Question.QuestionID}</td>` +
                `<td>${Question.QuestionText}</td>` +
                `<td>${CorrectAnswerText}</td>` +
                `<td>${WrongAnswerText1}</td>` +
                `<td>${WrongAnswerText2}</td>` +
                `<td>${WrongAnswerText3}</td>` +
                `<td class ='last'>` +
                '<div>' +
                '    <input type="text" name ="Answer" id="Answer">' +
                '</div>'+
                `<button type="submit" class ='test' data-id='${Question.QuestionID},${document.getElementById("Answer")}'>Mark</button>`+
                `</td>` +
                `</tr>`;
//oForm.elements["name"].value       document.querySelector('Answer')   '<input type="text" id="myForm" name="Answer"  />'+
        }
        QuestionsHTML  += '</table>';
        document.getElementById("Quiz").innerHTML = QuestionsHTML ;
        let editButtons = document.getElementsByClassName("test"); //  This button takes you to the function edit quiz
        for (let button of editButtons) {
            button.addEventListener("click", editQuiz, );
        }
    });

    document.getElementById("submit").addEventListener("click", editQuiz);
}


function editQuiz(){
    let x = event.target.getAttribute("data-id");
alert(x)
}
