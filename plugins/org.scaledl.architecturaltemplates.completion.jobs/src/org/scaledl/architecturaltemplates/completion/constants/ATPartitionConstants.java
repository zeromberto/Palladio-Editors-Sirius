package org.scaledl.architecturaltemplates.completion.constants;

import java.util.HashSet;
import java.util.Set;

import org.palladiosimulator.simulizar.launcher.jobs.LoadSimuLizarModelsIntoBlackboardJob;
import org.palladiosimulator.simulizar.reconfiguration.storydiagram.jobs.LoadSDMModelsIntoBlackboardJob;

import de.uka.ipd.sdq.workflow.pcm.jobs.LoadPCMModelsIntoBlackboardJob;

public final class ATPartitionConstants {

    public static Set<String> PCM_FILES = new HashSet<String>() {

        private static final long serialVersionUID = 6737375727319070884L;

        {
            add("repository");
            add("system");
            add("resourceenvironment");
            add("allocation");
            add("usagemodel");
            add("resourcetype");
            add("pcmmeasuringpoint");
            add("measuringpoint");
            add("emfprofile_diagram");
            add("xmi");
            add("usageevolution");
            add("dlim");
            add("monitorrepository");
        }
    };

    @SuppressWarnings("serial")
    public enum Partition {

        PCM("PCM", LoadPCMModelsIntoBlackboardJob.PCM_MODELS_PARTITION_ID, PCM_FILES),

        ORIGINAL_PCM("Original PCM", LoadSimuLizarModelsIntoBlackboardJob.ORIGINAL_PCM_MODELS_PARTITION_ID, PCM_FILES),

        EVENT_MIDDLEWARE("Event Middleware", LoadPCMModelsIntoBlackboardJob.EVENT_MIDDLEWARE_PARTITION_ID,
                new HashSet<String>() {

                    {
                        add("repository");
                    }
                }),

        RMI_MIDDLEWARE("RMI Middleware", LoadPCMModelsIntoBlackboardJob.RMI_MIDDLEWARE_PARTITION_ID,
                new HashSet<String>() {

                    {
                        add("repository");
                    }
                }),

        SDM("SDM", LoadSDMModelsIntoBlackboardJob.SDM_MODEL_PARTITION_ID, new HashSet<String>() {

            {
                add("sdm");
            }
        });

        private final String name;
        private final String partitionId;
        private final Set<String> fileNames;

        private Partition(final String name, final String partitionId, final Set<String> fileNames) {
            this.name = name;
            this.partitionId = partitionId;
            this.fileNames = fileNames;
        }

        public String getName() {
            return this.name;
        }

        public String getPartitionId() {
            return this.partitionId;
        }

        public Set<String> getFileNames() {
            return this.fileNames;
        }
    }

}
