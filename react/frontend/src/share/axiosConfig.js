// axiosConfig.js
import axios from 'axios';

const baseURL = 'https://your-api-endpoint.com'; // ベースURLを設定
const headers = {
    'Authorization': localStorage.getItem("token"),
    'Content-Type': 'application/json',
};

const axiosInstance = axios.create({
    baseURL,
    headers,
});

export { axiosInstance, headers };