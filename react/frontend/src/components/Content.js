import React, { useState, useEffect } from 'react';
import axios from 'axios';

import { Grid } from '@material-ui/core'


function Content() {
	const [post, setPosts] = useState([])

	useEffect(() => {
		axios.get('http://localhost:8080/api/companyList')
			.then(res => {
				setPosts(res.data)
			})
	}, [])
	const getCardContent = getObj => {
		return (
			<tr>
				<td>{getObj.companyID}</td>
				<td>{getObj.companyName}</td>
				<td>{getObj.companyInformation}</td>
				<td>{getObj.address}</td>
				<td>{getObj.url}</td>
				<td>{getObj.updateDate}</td>
				<td>{getObj.updateAPL}</td>
				<td>{getObj.registrationDate}</td>
				<td>{getObj.registrationAPL}</td>
				<td>{getObj.version}</td>
			</tr>
		);
	};
	return (
		<Grid container spacing={2}>
			<table style={{ minWidth: "400px" }}>
				<thead>
					<tr>
						<th>会社ID</th>
						<th>会社名</th>
						<th>会社概要</th>
						<th>住所</th>
						<th>URL</th>
						<th>更新日時</th>
						<th>更新APL</th>
						<th>登録日時</th>
						<th>登録APL</th>
						<th>VERSION</th>
					</tr>
				</thead>
				<tbody>
					{post.map(contentObj => getCardContent(contentObj))}
				</tbody>
			</table>

		</Grid>
	)
}

export default Content
