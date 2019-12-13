function pageLoad() {

    let resultsDiv = document.getElementById("searchResults");

    let resultsHTML = '';

    for (let i = 0; i < 100; i++) {

        resultsHTML += `<div><a href="https://www.bbc.co.uk">test x${i}</a></div>`;

    }

    resultsDiv.innerHTML = resultsHTML;

}