import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Comment } from '../models/comment.model';

@Injectable({
  providedIn: 'root'
})
export class CommentService {
  private apiUrl = 'http://localhost:8080/api';

  constructor(private http: HttpClient) {}

  // Get recent comments (for home page)
  getRecentComments(limit: number = 3): Observable<Comment[]> {
    return this.http.get<Comment[]>(`${this.apiUrl}/comments/recent?limit=${limit}`);
  }

  // Get all comments
  getAllComments(): Observable<Comment[]> {
    return this.http.get<Comment[]>(`${this.apiUrl}/comments`);
  }

  // Create new comment (requires authentication)
  createComment(content: string): Observable<Comment> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${localStorage.getItem('authToken')}`
    });
    
    return this.http.post<Comment>(`${this.apiUrl}/comments`, { content }, { headers });
  }

  // Delete comment (requires authentication)
  deleteComment(commentId: number): Observable<void> {
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${localStorage.getItem('authToken')}`
    });
    
    return this.http.delete<void>(`${this.apiUrl}/comments/${commentId}`, { headers });
  }
}
