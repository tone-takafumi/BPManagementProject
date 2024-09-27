import { useState } from "react";
import axios from "axios";
import { BrowserRouter as useNavigate } from "react-router-dom";

const Login = () => {
	const [login, setLogin] = useState({
		userName: "",
		password: "",
	});
	
	const navigate = useNavigate();

	const handleInput = (e) => {
		setLogin((prev) => ({ ...prev, [e.target.name]: e.target.value }));
	};

	const handleSubmit = async (e) => {
		e.preventDefault();
		try {
			const response = await axios.post("http://localhost:8080/auth/login", login, {
				withCredentials: true
			});
			localStorage.setItem("token", response.data);

			console.log(response);
			
			// /content に遷移
			navigate("/content");


			// ログイン成功後、リダイレクトなど
		} catch (error) {
			console.error("Login failed:", error);
		}
	};

	return (
			<form onSubmit={handleSubmit}>
				<input type="text" name="userName" onChange={handleInput} />
				<input type="password" name="password" onChange={handleInput} />
				<button type="submit">Login</button>
			</form>
	);
};

export default Login;