"use strict"

const baseURL = "http://localhost:8080";

axios.get(`${baseURL}/`)
    .then(res => { // handle response with callback
        console.log(res);
        console.log("DATA: ", res.data);
    }).catch(err => console.log(err)); // handle error

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

            getAllOutput.innerHTML = ""; //blanks an element


            lego.forEach(legoKit => renderLego(legoKit, getAllOutput));
        }).catch(err => console.log(err));
}


const renderLego = (legoKit, outputDiv) => {
    const newLegoKit = document.createElement('form');
    newLegoKit.classList.add("col");
    newLegoKit.classList.add("card");

    const seriesName = document.createElement("label");
    seriesName.innerText = "Series Name:";
    newLegoKit.appendChild(seriesName);
    const seriesNameTextBox = document.createElement("input");
    seriesNameTextBox.type = "text";
    seriesNameTextBox.value = legoKit.seriesName;
    newLegoKit.appendChild(seriesNameTextBox);


    const kitNumber = document.createElement("label");
    kitNumber.innerText = `Kit Number:`;
    newLegoKit.appendChild(kitNumber);
    const seriesKitNumBox = document.createElement("input");
    seriesKitNumBox.type = "number";
    seriesKitNumBox.value = legoKit.kitNumber;
    newLegoKit.appendChild(seriesKitNumBox);


    const kitName = document.createElement("label");
    kitName.innerText = `Kit Name:`;
    newLegoKit.appendChild(kitName);
    const seriesKitNameBox = document.createElement("input");
    seriesKitNameBox.type = "text";
    seriesKitNameBox.value = legoKit.kitName;
    newLegoKit.appendChild(seriesKitNameBox);


    const releaseYear = document.createElement("label");
    releaseYear.innerText = `Release Year:`;
    newLegoKit.appendChild(releaseYear);
    const seriesreleaseYearBox = document.createElement("input");
    seriesreleaseYearBox.type = "number";
    seriesreleaseYearBox.value = legoKit.releaseYear;
    newLegoKit.appendChild(seriesreleaseYearBox);


    const updateButton = document.createElement("button");
    updateButton.type = "submit";
    updateButton.innerText = "UPDATE";
    updateButton.classList.add("btn", "btn-primary");
    updateButton.addEventListener('click', (e) => replaceLegoKit(legoKit.id, e));
    newLegoKit.appendChild(updateButton);


    const deleteButton = document.createElement('button');
    deleteButton.innerText = "DELETE";
    deleteButton.classList.add("btn", "btn-primary");
    deleteButton.addEventListener('click', () => deleteLegoKit(legoKit.id));
    newLegoKit.appendChild(deleteButton);


    outputDiv.appendChild(newLegoKit);

    const gap = document.createElement('br');
    outputDiv.appendChild(gap);
}

const replaceLegoKit = (id, e) => {
    e.preventDefault(); // stops the form submitting in the default way

    console.log("in here"); //testing these functions are called
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

// const getByKitName = () => {
//     axios.get(`${baseURL}/getByKitName/${kitName.value}`)
//         .then(res => {
//             const LegoKit = res.data;
//             getByNameOutput.innerHTML = "";
//             renderLego(LegoKit, getByNameOutput);
//         }).catch(err => console.log(err));
//}

document.querySelector("section#postSection > form").addEventListener('submit', (e) => {
    e.preventDefault(); // stops the form submitting in the default way

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

            form.reset(); //resets form
            form.seriesName.focus(); // selects the name input
        }).catch(err => console.log(err));
});


getAllLegoKits();