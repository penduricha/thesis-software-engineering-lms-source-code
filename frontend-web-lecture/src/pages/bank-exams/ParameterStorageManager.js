export default class ParameterStorageManager {
    constructor() {
        // Initialize the array for parameters and load from session storage if available
        this.listParameter = [];
        this.loadFromSessionStorage();
    }

    // Method to add a parameter
    addParameter(nameParameter, dataTypeParameter) {
        const parameter = {
            name: nameParameter,
            dataType: dataTypeParameter,
        };
        this.listParameter.push(parameter);
        this.saveToSessionStorage();
    }

    // Method to remove a parameter by index
    removeParameter(index) {
        if (index >= 0 && index < this.listParameter.length) {
            this.listParameter.splice(index, 1);
            this.saveToSessionStorage();
        } else {
            console.error('Index out of bounds');
        }
    }

    // Save parameters to session storage
    saveToSessionStorage() {
        sessionStorage.setItem('listParameter', JSON.stringify(this.listParameter));
    }

    // Load parameters from session storage
    loadFromSessionStorage() {
        const storedParameters = sessionStorage.getItem('listParameter');
        if (storedParameters) {
            this.listParameter = JSON.parse(storedParameters);
        }
    }

    // Method to get all parameters
    getAllParameters() {
        return this.listParameter;
    }
}