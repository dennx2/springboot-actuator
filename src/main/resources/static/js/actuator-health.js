// Fetch the data from the API endpoint
fetch("http://localhost:8080/actuator/health")
    .then(response => response.json())
    .then(data => {
		displayOverallHealth(data);
        displayComponentStatus(data, "db");
        displayComponentStatus(data, "diskSpace");
        displayComponentStatus(data, "internet");
        displayComponentStatus(data, "ping");
    })
    .catch(error => console.error("Error fetching data:", error));

const displayComponentStatus = (data, componentName) => {
    const component = data.components[componentName];
    if (component) {
        const table = document.querySelector("table");
        const row = table.insertRow();
        const cell1 = row.insertCell(0);
        const cell2 = row.insertCell(1);
        cell1.textContent = componentName;
        cell2.textContent = component.status;
    } else {
        console.log(`Component not found: ${componentName}`);
    }
};

const displayOverallHealth = (data) => {
	const overallStatusElement = document.querySelector("#overallStatus");
	overallStatusElement.textContent = `Overall: ${data.status}`;
}
