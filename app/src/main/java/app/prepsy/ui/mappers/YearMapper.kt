package app.prepsy.ui.mappers

import app.prepsy.domain.entities.YearEntity
import app.prepsy.ui.models.Year
import javax.inject.Inject

class YearMapper @Inject constructor(): Mapper<Year, YearEntity> {
    override fun from(e: YearEntity): Year = Year(id = e.id, name = e.name)

    override fun to(t: Year): YearEntity = YearEntity(id = t.id, name = t.name)
}