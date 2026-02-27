import { Component } from '@angular/core';
import { AsyncPipe } from '@angular/common';
import {ToastService} from '../toast';

@Component({
  selector: 'app-toast-container',
  standalone: true,
  imports: [AsyncPipe],
  templateUrl: './toast-container.html',
  styleUrl: './toast-container.scss'
})
export class ToastContainerComponent {
  constructor(public toast: ToastService) {}
}
