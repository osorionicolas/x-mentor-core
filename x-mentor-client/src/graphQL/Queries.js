import {gql} from "@apollo/client"

export const LOAD_COURSES = gql`
    query Courses($query: String!, $page: Int!){ 
        courses(query: $query, page: $page) { 
            id
            title
            topic
        } 
    }
`

export const LOAD_COURSE = gql`
    query Course($id: ID!){ 
        course(id: $id) { 
            id
            title
            topic
        } 
    }
`