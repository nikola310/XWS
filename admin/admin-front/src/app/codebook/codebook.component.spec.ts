import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CodebookComponent } from './codebook.component';

describe('CodebookComponent', () => {
  let component: CodebookComponent;
  let fixture: ComponentFixture<CodebookComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CodebookComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CodebookComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
