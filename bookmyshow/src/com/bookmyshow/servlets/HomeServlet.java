package com.bookmyshow.servlets;

import com.bookmyshow.dao.MovieDAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/movies")
public class HomeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Get movies from database
        MovieDAO movieDAO = new MovieDAO();
        List<String> movies = movieDAO.getAllMovies();
        
        // Add sample movies if database is empty
        if (movies.isEmpty()) {
            movieDAO.addMovie("Avengers: Endgame");
            movieDAO.addMovie("The Dark Knight");
            movieDAO.addMovie("Inception");
            movies = movieDAO.getAllMovies();
        }
        
        request.setAttribute("movies", movies);
        request.getRequestDispatcher("/movies.jsp").forward(request, response);
    }
}
