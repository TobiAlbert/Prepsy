package app.prepsy.data.mapper

import app.prepsy.data.models.YearData
import app.prepsy.domain.entities.YearEntity
import javax.inject.Inject

class YearsDataMapper @Inject constructor(): Mapper<YearEntity, YearData> {

    override fun from(e: YearData): YearEntity = YearEntity(id = e.id, name = e.name)

    override fun to(t: YearEntity): YearData = YearData(id = t.id, name = t.name)
}