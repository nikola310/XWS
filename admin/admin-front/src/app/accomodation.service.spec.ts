import { TestBed, inject } from '@angular/core/testing';

import { AccomodationService } from './accomodation.service';

describe('AccomodationService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [AccomodationService]
    });
  });

  it('should be created', inject([AccomodationService], (service: AccomodationService) => {
    expect(service).toBeTruthy();
  }));
});
