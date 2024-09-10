import './App.css';
import { Grid } from '@material-ui/core';
import Header from './components/Header';
import Content from './components/Content';
import CompanyAdd from './components/CompanyAdd';

import {
  BrowserRouter as Router,
  Route,
  Routes
} from "react-router-dom";

function App() {
  return (
    <Grid container direction="column">
      <Grid item>
        <Header />
      </Grid>
      <Grid item container>
        <Grid sm={2} />
        <Grid xs={12} sm={8}>
        <Router>
          <Routes>
            <Route path="/" element={<Content />} />
            <Route path="/companyAdd" element={<CompanyAdd />} />
          </Routes>
        </Router>
        </Grid>
        <Grid sm={2} />
      </Grid>
    </Grid>
  );
}

export default App;