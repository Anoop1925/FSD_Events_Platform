import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { CommonModule } from '@angular/common';
import { NavbarComponent } from './components/shared/navbar/navbar.component';
import { ChatbotIconComponent } from './components/shared/chatbot/chatbot-icon.component';
import { ChatbotSidebarComponent } from './components/shared/chatbot/chatbot-sidebar.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    CommonModule,
    RouterOutlet,
    NavbarComponent,
    ChatbotIconComponent,
    ChatbotSidebarComponent
  ],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'FestFlex Events Management';
  
  constructor() {
    // Initialize application
    console.log('🚀 FestFlex Application Started');
    console.log('🚀 NavbarComponent imported:', !!NavbarComponent);
    console.log('🚀 ChatbotIconComponent imported:', !!ChatbotIconComponent);
    console.log('🚀 ChatbotSidebarComponent imported:', !!ChatbotSidebarComponent);
    console.log('🚀 App component constructor running');
  }
}
