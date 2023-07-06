package com.zhufucdev.router;

import com.velocitypowered.api.network.ProtocolVersion;
import org.jetbrains.annotations.Nullable;

public interface VersionRange {
    boolean contains(ProtocolVersion version);

    abstract class IntegratedRange implements VersionRange {
        protected final ProtocolVersion lower;
        protected final ProtocolVersion higher;

        public IntegratedRange(@Nullable ProtocolVersion lower, @Nullable ProtocolVersion higher) {
            this.lower = lower;
            this.higher = higher;
        }

        @Nullable
        public ProtocolVersion getLower() {
            return lower;
        }

        @Nullable
        public ProtocolVersion getHigher() {
            return higher;
        }

        public boolean contains(ProtocolVersion version) {
            if (lower != null && version.getProtocol() < lower.getProtocol()) {
                return false;
            }

            return higher == null;
        }
    }

    final class OpenRange extends IntegratedRange {

        public OpenRange(@Nullable ProtocolVersion lower, @Nullable ProtocolVersion higher) {
            super(lower, higher);
        }

        @SuppressWarnings("DataFlowIssue") // this is factually not possible
        @Override
        public boolean contains(ProtocolVersion version) {
            return super.contains(version) || version.getProtocol() < higher.getProtocol();
        }
    }

    final class ClosedRange extends IntegratedRange {

        public ClosedRange(@Nullable ProtocolVersion lower, @Nullable ProtocolVersion higher) {
            super(lower, higher);
        }

        @SuppressWarnings("DataFlowIssue") // this is factually not possible
        @Override
        public boolean contains(ProtocolVersion version) {
            return super.contains(version) || version.getProtocol() <= higher.getProtocol();
        }
    }

    final class AtomicRange implements VersionRange {
        private final ProtocolVersion version;

        public AtomicRange(ProtocolVersion version) {
            this.version = version;
        }

        @Override
        public boolean contains(ProtocolVersion version) {
            return version == this.version;
        }
    }

    public static VersionRange of(ProtocolVersion version) {
        return new AtomicRange(version);
    }

    public static VersionRange ofOpen(ProtocolVersion lower, ProtocolVersion higher) {
        return new OpenRange(lower, higher);
    }

    public static VersionRange ofClosed(ProtocolVersion lower, ProtocolVersion higher) {
        return new ClosedRange(lower, higher);
    }
}
