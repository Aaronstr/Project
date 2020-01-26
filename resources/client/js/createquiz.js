function  pageLoad() {
    // The code below sets up the table
    let quizHTML  = '<table>' +
        '<tr>' +
        '<th>QuizID</th>' +
        '<th>QuizName</th>' +
        '<th>Username</th>' +
        '</tr>';

    fetch('/Quiz/Select2/' + Cookies.get("Username"), {method: 'get'}  // This fetches all quizzes created by the logged in user.

    ).then(response => response.json()
    ).then(Quizzes => {
        for (let Quiz of Quizzes) {
            quizHTML  += '<tr>' +
                `<td>${Quiz.QuizID}</td>` +
                `<td>${Quiz.QuizName}</td>` + //This code inputs each quiz into the table
                `<td>${Quiz.Username}</td>` +
                `<td class ='last'>` +
                `<button class ='editButton' data-id='${Quiz.QuizID}'>EDIT</button>` +
                `<button class ='deleteButton' data-id='${Quiz.QuizID}'>DELETE</button>` +
                `</td>` +
                `</tr>`;

        }
        quizHTML  += '</table>';
        document.getElementById("listQuiz").innerHTML = quizHTML ; //puts table into html

        let editButtons = document.getElementsByClassName("editButton"); //  This button takes you to the function edit quiz
        for (let button of editButtons) {
            button.addEventListener("click", editQuiz);
        }
        let deleteButtons = document.getElementsByClassName("deleteButton");          //  This button takes you to the function delete quiz
        for (let button of deleteButtons) {
            button.addEventListener("click", deleteQuiz);
        }

    });
    document.getElementById("saveButton").addEventListener("click", saveEditQuiz);          // These listens to the buttons im the quiz form.
    document.getElementById("saveQuestionButton").addEventListener("click", saveQuestionQuiz);
}



function saveQuestionQuiz(event) {
    event.preventDefault(); // First it checks that it has the minimum fields to create a question and quiz.
    if (document.getElementById("QuizQuestionID").value.trim() === '') {
        alert("Please provide a QuizID")
        return;
    }
    if (document.getElementById("Timer").value.trim() === '') {
        alert("Please provide a Timer")
        return;
    }
    if (document.getElementById("QuestionText").value.trim() === '') {
        alert("Please provide a QuestionText")
        return;
    }
    if (document.getElementById("QuestionType").value.trim() === '') {
        alert("Please provide a QuestionType")
        return;
    }
    if (document.getElementById("CorrectAnswerText").value.trim() === '') {
        alert("Please provide a CorrectAnswerText")
        return;
    }

    // question data from table

    // LAYER 1 - CREATE QUESTION //
    const form = document.getElementById("QuestionForm");
    const formData = new FormData(form);
    fetch("/Question/Create", {method: 'post', body: formData}
    ).then(response => response.json()
    ).then(responseData => {

        if (responseData.hasOwnProperty('error')) {
            alert(responseData.error);
        } else {
            alert("Created Question")
            // LAYER 2 - GET THE MOST RECENT QUESTION //
            // This is the ID of the question which was just created. I need this inorder to add the answer to the right question.
            let QuestionID = document.getElementById("QuizQuestionID").value;
            fetch('/Question/Select/'+QuestionID, {method: 'get'}
            ).then(response => response.json()
            ).then(Questions => {

                let Question = "0";
                for (let questions of Questions) {
                     Question = questions.QuestionID;
                }


                // LAYER 3 - CREATE ANSWER BASED ON QUESTION TYPE //

                if (document.getElementById("QuestionType").value.trim() === '1') {
                    // type one data from table
                    const form = document.getElementById("QuestionForm");
                    const formData = new FormData(form);
                    formData.append("QuestionID", Question);

                    fetch("/AnswerTypeOne/Create", {method: 'post', body: formData}
                    ).then(response => response.json()
                    ).then(responseData => {

                        if (responseData.hasOwnProperty('error')) {
                            alert(responseData.error);
                        } else {
                            alert("Created Type one answer")
                            // LAYER 4a - ANSWER TYPE 1 MADE //

                        }
                    });
                } else {
                    // type two data from table
                    const form = document.getElementById("QuestionForm");
                    const formData = new FormData(form);
                    formData.append("QuestionID", Question);
                    fetch("/AnswerTypeTwo/Create", {method: 'post', body: formData}
                    ).then(response => response.json()
                    ).then(responseData => {

                        if (responseData.hasOwnProperty('error')) {
                            alert(responseData.error);
                        } else {
                            alert("Created Type two answer")
                            // LAYER 4b - ANSWER TYPE 2 MADE //

                        }
                    });

                }

            });

        }
    });

}

function editQuiz(event) { <!-- This functions just take the selected data from the list quiz and places it into the quizform -->
    const  id = event.target.getAttribute("data-id");
    fetch('/Quiz/Select3/'+id,{method:'get'} <!-- Makes sure the data is up to date -->
    ).then(response => response.json()
    ).then(Quiz=>{
        if(Quiz.hasOwnProperty('error')){
            alert(Quiz.error);
        }else{
            document.getElementById("QuizID").value = id ;
            document.getElementById("QuizName").value = Quiz.QuizName;
        }
    });

}
function saveEditQuiz(event) { // This function either creates a new quiz or edits an old quiz
    event.preventDefault();
    if(document.getElementById("QuizName").value.trim() ==='') { //This makes sure it has a quizname which is needed for creating and editing a quiz
        alert("Please provide a quiz name")
        return;
    }
    const id = document.getElementById("QuizID").value;
    //The if statement decides  to create a new quiz or edit an old one. If no ID is entered it creates a new quiz else it edits the quiz with the quizID. -->
    if (id === '') {
        const form = document.getElementById("QuizForm");
        const formData = new FormData(form);
        formData.append("Username", Cookies.get("Username"));
        fetch("/Quiz/Create", {method: 'post', body: formData} // Creating a new quiz
        ).then(response => response.json()
        ).then(responseData => {

            if (responseData.hasOwnProperty('error')) {
                alert(responseData.error);
            } else {
                // New quiz created
                pageLoad();
            }
            pageLoad();
        });

    } else {
        const form = document.getElementById("QuizForm");
        const formData = new FormData(form);
        fetch("/Quiz/Update", {method: 'post', body: formData} //Editing an old quiz
        ).then(response => response.json()
        ).then(responseData => {

            if (responseData.hasOwnProperty('error')) {
                alert(responseData.error);
            } else {
                pageLoad();
            }
            pageLoad();
        });

    }


}

function deleteQuiz(event){ // This functions deletes quizzs
    const ok = confirm("This is permanent, are you sure?");
    if(ok == true){
        let QuizID = event.target.getAttribute("data-id");
        let formData = new FormData();
        formData.append("QuizID", QuizID);

        fetch('/Quiz/Delete',{method: 'post',body:formData} // This deletes the quiz with the quizID, it also deletes all questions with that quizID.
       // It will then delete all answers with that QuestionID. It does this in the database
        ).then(response => response.json()
        ).then(responseData =>{
                if(responseData.hasOwnProperty('error')){
                    alert(responseData.error);
                }else{
                    pageLoad();
                }
            }
        )
        pageLoad();
    }




}