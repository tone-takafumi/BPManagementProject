import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { TextField, Button, Grid } from '@material-ui/core';


function About() {

	const [company, setCompany] = useState({
		companyID: "",
		companyName: "",
		companyInformation: "",
		address: "",
		url: "",
		version: 1
	});

	const handleCompany = (e) => {
		setCompany((prev) => ({ ...prev, [e.target.name]: e.target.value }));
	};

	useEffect(() => {
		axios.get('http://localhost:8080/api/companyMaxId')
			.then(res => {
				setCompany((prev) => ({ ...prev, companyID: res.data }))
			})
	}, [])

	// Saveボタン押下
	const onClick = (e) => {
		e.preventDefault();

		const formData = new FormData();
		formData.append('company', company);

		axios.post('http://localhost:8080/api/company', company)

			.catch((error) => {
				console.log(error);
			});
	};




	return (
		<div>
			<form>
				<Grid container direction="column">
				<Grid item>
				会社ID
				<TextField
					name="companyID"
					onChange={handleCompany}
					placeholder="会社名"
					value={company.companyID}
				/>
				</Grid>
				<Grid item>
				会社名
				<TextField
					name="companyName"
					onChange={handleCompany}
					placeholder="会社名"
				/>
				</Grid>
				<Grid item>
				会社概要
				<TextField
					name="companyInformation"
					onChange={handleCompany}
					type="text"
					placeholder="会社概要"
				/>
				</Grid>
				<Grid item>
				住所
				<TextField
					name="address"
					onChange={handleCompany}
					type="text"
					placeholder="住所"
				/>
				</Grid>
				<Grid item>
				URL
				<TextField
					name="url"
					onChange={handleCompany}
					type="text"
					placeholder="URL"
				/>
				</Grid>
				<Grid item>
				<Button
					variant="contained"
					onClick={onClick}
				>
				登録
				</Button>
				</Grid>
				</Grid>
			</form>




		</div>
	)
}

export default About
