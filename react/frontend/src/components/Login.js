import { useState } from "react";
import axios from "axios";
import { BrowserRouter as Router, Routes, Route, useNavigate } from "react-router-dom";
import MyDialog from "../share/customDialog";

const Login = () => {
	const [login, setLogin] = useState({
		userName: "",
		password: "",
	});

	const [dialog, setDialog] = useState({ open: false, message: ""});
	
	const handleClose = () => setDialog({ open: false, message: "" });

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
			
			// /content に遷移
			navigate("/content");

			// ログイン成功後、リダイレクトなど
		} catch (error) {
			if(error.response.status === 403){
				setDialog({open: true, message: "ユーザ名またはパスワードが違います。"});
			}
		}
	};

	return (
		<div>
			<form onSubmit={handleSubmit}>
				<input type="text" name="userName" onChange={handleInput} />
				<input type="password" name="password" onChange={handleInput} />
				<button type="submit">Login</button>
			</form>
			<MyDialog open={dialog.open} message={dialog.message} onClose={handleClose} />
		</div>
	);
};

export default Login;