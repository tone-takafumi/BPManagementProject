// axiosConfig.js
import axios from 'axios';

// ベースURLを設定
const baseURL = 'https://your-api-endpoint.com';
const headers = {
	'Authorization': localStorage.getItem("token"),
	'Content-Type': 'application/json',
};

const axiosInstance = axios.create({
	baseURL,
	headers: {
		Authorization: `Bearer ${localStorage.getItem("token")}`,
	}
});

// リクエストインターセプターの設定
axiosInstance.interceptors.request.use(
	(config) => {
		// ローカルストレージからトークンを取得
		const token = localStorage.getItem('token');
		if (token) {
			// トークンがあればAuthorizationヘッダーに設定
			config.headers['Authorization'] = `Bearer ${token}`;
		}
		return config;
	},
	(error) => {
		// エラー時の処理
		return Promise.reject(error);
	}
);

export { axiosInstance, headers };