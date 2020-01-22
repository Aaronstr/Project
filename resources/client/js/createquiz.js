function  pageLoad() {
    let quizHTML  = '<table>' +
        '<tr>' +
        '<th>QuizID</th>' +
        '<th>QuizName</th>' +
        '<th>Username</th>' +
        '</tr>';

    fetch('/Quiz/Select2/' + Cookies.get("Username"), {method: 'get'}
    ).then(response => response.json()
    ).then(Quizzes => {
        for (let Quiz of Quizzes) {
            quizHTML  += '<tr>' +
                `<td>${Quiz.QuizID}</td>` +
                `<td>${Quiz.QuizName}</td>` +
                `<td>${Quiz.Username}</td>` +
                `<td class ='last'>` +
                `<button class ='editButton' data-id='${Quiz.QuizID}'>EDIT</button>` +
                `<button class ='deleteButton' data-id='${Quiz.QuizID}'>DELETE</button>` +
                `</td>` +
                `</tr>`;

        }
        quizHTML  += '</table>';
        document.getElementById("listQuiz").innerHTML = quizHTML ;

        let editButtons = document.getElementsByClassName("editButton");
        for (let button of editButtons) {
            button.addEventListener("click", editQuiz);
        }
        let deleteButtons = document.getElementsByClassName("deleteButton");
        for (let button of deleteButtons) {
            button.addEventListener("click", deleteQuiz);
        }

    });
    document.getElementById("saveButton").addEventListener("click", saveEditQuiz);
    document.getElementById("saveQuestionButton").addEventListener("click", saveQuestionQuiz);
}



function saveQuestionQuiz(event) {
    event.preventDefault();
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

            let QuestionID = document.getElementById("QuizQuestionID").value;
            fetch('/Question/Select/'+QuestionID, {method: 'get'}
            ).then(response => response.json()
            ).then(Questions => {

                let Question = "0";
                for (let questions of Questions) {
                     Question = questions.QuestionID;
                }


                // LAYER 3 - DO SOMETHING BASED ON QUESTION TYPE //

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

function editQuiz(event) {
    const  id = event.target.getAttribute("data-id");
    fetch('/Quiz/Select3/'+id,{method:'get'}
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
function saveEditQuiz(event) {
    event.preventDefault();
    if(document.getElementById("QuizName").value.trim() ==='') {
        alert("Please provide a quiz name")
        return;
    }
    const id = document.getElementById("QuizID").value;

    if (id === '') {
        const form = document.getElementById("QuizForm");
        const formData = new FormData(form);
        formData.append("Username", Cookies.get("Username"));
        fetch("/Quiz/Create", {method: 'post', body: formData}
        ).then(response => response.json()
        ).then(responseData => {

            if (responseData.hasOwnProperty('error')) {
                alert(responseData.error);
            } else {
                pageLoad();
            }
            pageLoad();
        });

    } else {
        const form = document.getElementById("QuizForm");
        const formData = new FormData(form);
        fetch("/Quiz/Update", {method: 'post', body: formData}
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

function deleteQuiz(event){
    const ok = confirm("This is permanent, are you sure?");
    if(ok == true){
        let QuizID = event.target.getAttribute("data-id");
        let formData = new FormData();
        formData.append("QuizID", QuizID);

        fetch('/Quiz/Delete',{method: 'post',body:formData}
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