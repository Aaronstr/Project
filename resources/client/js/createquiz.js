function  pageLoad() {
    let quizHTML  = '<table>' +
        '<tr>' +
        '<th>QuizID</th>' +
        '<th>QuizName</th>' +
        '<th>Username</th>' +
        '</tr>';
    fetch('/Quiz/Select', {method: 'get'}
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
    document.getElementById("cancelButton").addEventListener("click",cancelEditQuiz);
}


function editQuiz(event) {


}
function cancelEditQuiz(event) {

}




function cancelEditTeam(event) {

}




function deleteQuiz(event){
    const ok = confirm("This is permanent, are you sure?");
    if(ok == true){
        let QuizID = event.target.getAttribute("data-id");
        let formData = new FormData();
        formData.append("QuizID", QuizID);
        fetch('/Question/Select', {method: 'get',body: formData}
        ).then(response => response.json()
        ).then(Questions => {
            for (let Question of Questions) {
                let x = Question.QuestionID;
                const formData = new FormData(x);
                fetch('/AnswerTypeTwo/Delete3', {method: 'post', body: formData}
                ).then(response => response.json()
                ).then(responseData => {
                        if (responseData.hasOwnProperty('error')) {
                            alert(responseData.error);
                        } else {

                        }
                    }
                )
                fetch('/AnswerTypeOne/Delete3', {method: 'post', body: formData}
                ).then(response => response.json()
                ).then(responseData => {
                        if (responseData.hasOwnProperty('error')) {
                            alert(responseData.error);
                        } else {

                        }
                    }
                )
            }
        });
        fetch('/Question/Delete1',{method: 'post',body:formData}
        ).then(response => response.json()
        ).then(responseData =>{
                if(responseData.hasOwnProperty('error')){
                    alert(responseData.error);
                }else{

                }
            }
        )

        fetch('/Quiz/Delete',{method: 'post',body:formData}
        ).then(response => response.json()
        ).then(responseData =>{
                if(responseData.hasOwnProperty('error')){
                    alert(responseData.error);
                }else{

                }
            }
        )
        pageLoad();
    }




}