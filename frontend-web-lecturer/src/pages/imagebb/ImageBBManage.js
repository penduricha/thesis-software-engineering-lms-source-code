import axios from "axios";

export default class ImageBBManage {
    static async deleteImage(deleteUrl) {
        try {
            const response = await axios.delete(deleteUrl);
            if (response.data.success) {
                console.log("Image deleted successfully");
            } else {
                console.error("Error deleting image: ", response.data.message);
            }
        } catch (error) {
            console.error("Error deleting image: ", error);
            alert("Error deleting image: " + error);
        }
    }

    static async uploadImage(selectFileImage){
        const formData = new FormData();
        formData.append('image', selectFileImage);
        try {
            //return response.data.data.url;
            return await axios.post(
                'https://api.imgbb.com/1/upload?key=e43c98a286cf7743092254f845c26e56', formData, {
                    headers: {
                        'Content-Type': 'multipart/form-data'
                    }
                });
        } catch (error) {
            console.error("Error uploading image: ", error);
            alert(error);
        }
    }
}