"use strict"

const baseURL = "http://localhost:8080";

axios.get(`${baseURL}/`)
    .then(res => { 
        console.log(res);
        console.log("DATA: ", res.data);
    }).catch(err => console.log(err)); 

console.log("Have we got a response yet?");

const getAllSection = document.querySelector("#getAllSection");
const getByIdOutput = document.querySelector("#getByIdOutput");
const getByNameOutput = document.querySelector("#getByNameOutput");

const legoId = document.querySelector("#LegoId");
const kitName = document.querySelector("#getKitName");

const getAllLegoKits = () => {
    axios.get(`${baseURL}/getAllLegoKits`)
        .then(res => {
            const lego = res.data;

            getAllOutput.innerHTML = ""; 


            lego.forEach(legoKit => renderLego(legoKit, getAllOutput));
        }).catch(err => console.log(err));
}

const replaceLegoKit = (e, id) => {
    e.preventDefault(); 

    console.log("in here"); 
    console.log(e);

    const form = e.target;

    const data = {
        seriesName: form.seriesName.value,
        kitNumber: form.kitNumber.value,
        kitName: form.kitName.value,
        releaseYear: form.releaseYear.value
    }

    axios.put(`${baseURL}/replaceLegoKit/${id}`, data)
        .then(res => {
            console.log(res);
            getAllLegoKits();
            alert("Successfully Updated Lego Kit!")
        }).catch(err => console.log(err));

}

const renderLego = (legoKit, outputDiv) => {
    const newLegoKit = document.createElement('form');
    newLegoKit.addEventListener('submit', (e) => replaceLegoKit(e, legoKit.id));
    const deleteButton = document.createElement('button');
    deleteButton.addEventListener('click', () => deleteLegoKit(legoKit.id));
    const updateButton = document.createElement("button");

    newLegoKit.classList.add("col");
    newLegoKit.classList.add("card");
    console.log(legoKit);

    const seriesName = document.createElement("label");
    seriesName.innerText = "Series Name:";
    newLegoKit.appendChild(seriesName);
    const seriesNameTextBox = document.createElement("input");
    seriesNameTextBox.name = "seriesName";
    seriesNameTextBox.type = "text";
    seriesNameTextBox.value = legoKit.seriesName;
    newLegoKit.appendChild(seriesNameTextBox);


    const kitNumber = document.createElement("label");
    kitNumber.innerText = `Kit Number:`;
    newLegoKit.appendChild(kitNumber);
    const seriesKitNumBox = document.createElement("input");
    seriesKitNumBox.name = "kitNumber";
    seriesKitNumBox.type = "number";
    seriesKitNumBox.value = legoKit.kitNumber;
    newLegoKit.appendChild(seriesKitNumBox);


    const kitName = document.createElement("label");
    kitName.innerText = `Kit Name:`;
    newLegoKit.appendChild(kitName);
    const seriesKitNameBox = document.createElement("input");
    seriesKitNameBox.name = "kitName";
    seriesKitNameBox.type = "text";
    seriesKitNameBox.value = legoKit.kitName;
    newLegoKit.appendChild(seriesKitNameBox);


    const releaseYear = document.createElement("label");
    releaseYear.innerText = `Release Year:`;
    newLegoKit.appendChild(releaseYear);
    const seriesreleaseYearBox = document.createElement("input");
    seriesreleaseYearBox.name = "releaseYear";
    seriesreleaseYearBox.type = "number";
    seriesreleaseYearBox.value = legoKit.releaseYear;
    newLegoKit.appendChild(seriesreleaseYearBox);



    updateButton.type = "submit";
    updateButton.innerText = "UPDATE";
    updateButton.classList.add("btn", "btn-primary");

    newLegoKit.appendChild(updateButton);

    deleteButton.innerText = "DELETE";
    deleteButton.classList.add("btn", "btn-primary");

    newLegoKit.appendChild(deleteButton);


    outputDiv.appendChild(newLegoKit);

    const gap = document.createElement('br');
    outputDiv.appendChild(gap);
}

const deleteLegoKit = id => {
    axios.delete(`${baseURL}/deleteLegoKit/${id}`)
        .then(res => {
            console.log(res);
            getAllLegoKits();
            alert("Successfully Deleted Lego Kit!")
        }).catch(err => console.log(err));
}

const getLegoKitById = () => {
    axios.get(`${baseURL}/getLegoKit/${legoId.value}`)
        .then(res => {
            const LegoKit = res.data;
            getByIdOutput.innerHTML = "";
            renderLego(LegoKit, getByIdOutput);
        }).catch(err => console.log(err));

}

document.querySelector("section#postSection > form").addEventListener('submit', (e) => {
    e.preventDefault(); 

    console.log("THIS: ", this);
    console.log("kitNumber: ", this.kitNumber);

    const form = e.target;

    const data = {
        seriesName: form.seriesName.value,
        kitNumber: form.kitNumber.value,
        kitName: form.kitName.value,
        releaseYear: form.releaseYear.value
    }

    console.log("DATA: ", data);

    axios.post(`${baseURL}/createLegoKit`, data)
        .then((res) => {
            console.log(res);
            getAllLegoKits();

            form.reset(); 
            form.seriesName.focus(); 
        }).catch(err => console.log(err));
});


getAllLegoKits();