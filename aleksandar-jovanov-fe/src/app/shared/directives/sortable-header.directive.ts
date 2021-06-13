import { Directive, EventEmitter, Input, Output } from '@angular/core';

export type SortDirection = 'asc' | 'desc' | '';

export interface SortEvent {
  column: string;
  direction: SortDirection;
}

const rotate: {[key: string]: SortDirection} = { 'asc': 'desc', 'desc': '', '': 'asc' };


@Directive({
  selector: '[appSortableHeader]',
  host: {
    '[class.asc]': 'direction === "asc"',
    '[class.desc]': 'direction === "desc"',
    '(click)': 'rotate()'
  }
})
export class SortableHeaderDirective {
  @Input() sortable: string = '';
  @Input() direction: SortDirection = '';
  @Output() sort = new EventEmitter<SortEvent>();
  constructor() { }
  rotate() {
    this.direction = rotate[this.direction];
    this.sort.emit({column: this.sortable, direction: this.direction});
  }
}