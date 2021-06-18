import React, { useContext } from "react";
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Redirect
} from "react-router-dom";
import Home from './pages/HomePage'
import CoursePage from './pages/CoursePage'
import CourseListPage from './pages/CourseListPage'
import MyCoursesPage from './pages/MyCoursesPage'
import Header from "./components/Header";
import Footer from "./components/Footer";
import { NotificationsProvider } from "./providers/NotificationsProvider";
import { AuthContext, AuthProvider } from "./providers/AuthProvider";
import {
  ApolloClient,
  InMemoryCache,
  ApolloProvider,
  HttpLink,
  from
} from "@apollo/client"
import { onError } from "@apollo/client/link/error"
import { API_URL } from './environment'

const errorLink = onError(({ graphqlErrors, networkError}) => {
  if(graphqlErrors){
    graphqlErrors.map(({message, location, path}) => {
      alert(`Graphql error ${message}`)
    })
  }
  if(networkError){
    alert(`Network error ${networkError}`)
  }
})

const link = from([
  errorLink,
  new HttpLink({uri: `${API_URL}/graphql`})
])

const client = new ApolloClient({
  cache: new InMemoryCache,
  link: link
})

export default function App() {
  return (
    <ApolloProvider client={client}>
      <AuthProvider>
        <Router>
          <NotificationsProvider>
            <Header/>
            <Switch>
              <Route path="/courses">
                <CourseListPage />
              </Route>
              <PrivateRoute path="/my/courses">
                <MyCoursesPage />
              </PrivateRoute>
              <PrivateRoute path="/course/:id">
                <CoursePage />
              </PrivateRoute>
              <Route path="/">
                <Home />
              </Route>
            </Switch>
          </NotificationsProvider>
          <Footer />
        </Router>
      </AuthProvider>
    </ApolloProvider>
  )
}

function PrivateRoute({ children, ...rest }) {
  const { isLoggedIn } = useContext(AuthContext)
  return (
    <Route
      {...rest}
      render={({ location }) =>
        isLoggedIn ? (
          children
        ) : (
          <Redirect
            to={{
              pathname: "/",
              state: { from: location }
            }}
          />
        )
      }
    />
  )

}
