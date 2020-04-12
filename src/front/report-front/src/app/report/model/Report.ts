export class ReportEntry {

  constructor(public readonly filmId: number,
              public readonly filmName: string,
              public readonly characterId: number,
              public readonly characterName: string,
              public readonly planetId: number,
              public readonly planetName: string) {
  }
}

export class Report {
  constructor(public readonly reportId: number,
              public readonly queryCriteriaCharacterPhrase: string,
              public readonly queryCriteriaPlanetName: string,
              public readonly result: ReportEntry[]
  ) {
  }
}
