export default class SessionStorageQuestionJavaCoreChoose {
    constructor() {
        // Initialize the array for questions and load from session storage if available
        this.questions = [];
        this.loadFromSessionStorage();
    }

    // Function to add a question
    addQuestion(questionJavaCoreID, content, codeSample) {
        const question = {
            questionJavaCoreID: questionJavaCoreID,
            contentQuestion: content,
            codeSample: codeSample,
            score: 1,
        };
        this.questions.push(question);
        this.saveToSessionStorage();
    }

    // Function to remove a question by index
    removeQuestion(index) {
        if (index >= 0 && index < this.questions.length) {
            this.questions.splice(index, 1);
            this.saveToSessionStorage();
        } else {
            console.error('Index out of bounds');
        }
    }

    // Save questions to session storage
    saveToSessionStorage() {
        sessionStorage.setItem('questionsSelected', JSON.stringify(this.questions));
    }

    // Load questions from session storage
    loadFromSessionStorage() {
        const storedQuestions = sessionStorage.getItem('questionsSelected');
        if (storedQuestions) {
            this.questions = JSON.parse(storedQuestions);
        }
        return this.questions;
    }

    updateScore(index, newScore) {
        if (index >= 0 && index < this.questions.length) {
            this.questions[index].score = newScore;
            this.saveToSessionStorage();
        } else {
            console.error('Index out of bounds');
        }
    }

    // Function to get all questions
    getAllQuestions() {
        return this.questions;
    }
}