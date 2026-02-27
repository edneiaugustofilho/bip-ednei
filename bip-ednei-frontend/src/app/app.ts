import {Component, signal} from '@angular/core';
import {HomeComponent} from './home/home';
import {ToastContainerComponent} from './shared/toast/toast-container/toast-container';

@Component({
  selector: 'app-root',
  imports: [HomeComponent, ToastContainerComponent],
  templateUrl: './app.html',
  styleUrl: './app.scss'
})
export class App {
  protected readonly title = signal('bip-ednei-frontend');
}
